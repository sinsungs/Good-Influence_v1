import React, {useState, useEffect} from 'react';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';
import { useNavigate } from 'react-router-dom';


function RecommendPost() {

  const navigate = useNavigate();

  // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  // 모달창 State
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedField, setSelectedField] = useState(null);

  // 검색창 State
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  // 인플루언서 State
  const [ino, setino] = useState(null);
  const [secondino, setSecondino] = useState(null);
  const [thirdino, setThirdino] = useState(null);

  // 이미지 State
  const [file, setFile] = useState(null);
  const [imagePath, setImagePath] = useState('');

  const handleImageUpload = (event) => {
    const selectedImage = event.target.files[0];
    setFile(selectedImage);
  };


const handleModalOpen = (field) => {
  setSelectedField(field);
  setIsModalOpen(true);
};

const handleModalClose = () => {
  setIsModalOpen(false);
};
// 모달창 닫기 End


// 게시글 인플루언서 검색 Start
const handleSearchInputChange = (e) => {
  setSearchQuery(e.target.value);
};

const handleSearchSubmit = async (e) => {
  e.preventDefault();

  try {
    const response = await axios.post('/api/search', { name : searchQuery }); // POST 요청으로 변경
    const data = response.data;
    // alert(data);
    setSearchResults(data);
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
};
// 게시글 인플루언서 검색 End 


// 게시글 작성 Start
  const handleSubmit = (event) => {

    event.preventDefault();
  
    
    if (!jwtToken) {
      alert('로그인이 필요합니다.');
      return;
    }
    
    const title = event.target.elements.title.value;
    const content = event.target.elements.content.value;
    const ino = event.target.elements.ino.value;
    const secondino = event.target.elements.secondino.value;
    const thirdino = event.target.elements.thirdino.value;

    if (!title || !content || !ino || !secondino || !thirdino || !file) {
      alert('모든 필수 입력란을 채워주세요.');
      return;
    }

    const postData = {
      title: title,
      content: content,
      ino: ino,
      secondino: secondino,
      thirdino : thirdino
    };



    const formData  = new FormData();

    formData.append('dto', new Blob([JSON.stringify(postData)], { type: "application/json" }));
    formData.append('file', file);

    axios.post('/api/post/register', formData, {
      headers: {
        Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
      },
    })
      .then(response => {

        console.log('Server response:', response.data);

        alert('추천글 작성을 완료했습니다.', response.data);
        navigate('/list');

        // window.location.href = '/list';

      })
      .catch(error => {

        console.error('Error:', error);

      });
  };
// 게시글 작성 End


// 선택한 인플루언서 처리 로직 구현 (예: 상태 업데이트 또는 백엔드로 보내기)
  const handleInfluencerSelection = (influencer) => {
    setino(influencer);
    console.log('Selected influencer:', influencer);
  };

// 선택한 인플루언서 처리 로직 구현 (예: 상태 업데이트 또는 백엔드로 보내기) 


// 선택한 인플루언서 값을 수정하는 함수
const handleInfluencerEdit = (field, influencer) => {
  if (field === 'ino') {
    if ((secondino === influencer) || (thirdino === influencer)) {
      alert('중복된 추천 인플루언서 입니다.');
      return;
    }
    setino(influencer);
  } else if (field === 'secondino') {
    if ((ino === influencer) || (thirdino === influencer)) {
      alert('중복된 추천 인플루언서 입니다.');
      return;
    }
    setSecondino(influencer);
  } else if (field === 'thirdino') {
    if ((ino === influencer) || (secondino === influencer)) {
      alert('중복된 추천 인플루언서 입니다.');
      return;
    }
    setThirdino(influencer);
  }
  setIsModalOpen(false); // 모달 창 닫기
};





  return (
    
    <div className="App">
      <div className='back'>
      <div className="container">

        <div style={{ width: '100%'}}>

          <h1>행복한 세상을 만들어보아요</h1>
          <p>좋은 영향력을 가지고있는 인플루언서를 추천해주세요</p>

          <form onSubmit={handleSubmit}>
              <div>
                <label htmlFor="image">배경 이미지:</label>
                <input
                  type="file"
                  id="image"
                  accept="image/*" // 이미지 파일만 업로드 가능하도록 설정
                  onChange={handleImageUpload}
                />
              </div>

              <label>게시글 제목</label>
              <input type="text" className="custom-input" name="title" placeholder="제목을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <label>추천 내용</label>
              <input type="text" className="custom-input" name="content" placeholder="내용을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <label>추천 인플루언서</label>
              <p>* 인플루언서 3명을 모두 선택해 주세요.</p>

              <div style={{ display: 'flex', alignItems: 'center' }}>
              <div className="rectangle"   onClick={() => handleModalOpen('ino')}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
              </div>
              <input type="text" style={{width:'200px'}}className="custom-input" name="ino" value={ino} onClick={() => handleModalOpen('ino')} placeholder="첫번째 인플루언서를 선택해주세요."   readOnly/>
              <div style={{ height: '30px' }}></div>
              </div>

              <div style={{ display: 'flex', alignItems: 'center' }}>
              <div className="rectangle"   onClick={() => handleModalOpen('secondino')}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
              </div>
              <input type="text" style={{width:'200px'}} className="custom-input" name="secondino" value={secondino} onClick={() => handleModalOpen('secondino')} placeholder="두번째 인플루언서를 선택해주세요."   readOnly/>
              <div style={{ height: '30px' }}></div>
              </div>


              <div style={{ display: 'flex', alignItems: 'center' }}>
              <div className="rectangle"   onClick={() => handleModalOpen('thirdino')}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
              </div>
              <input type="text" style={{width:'200px'}} className="custom-input" name="thirdino" value={thirdino} onClick={() => handleModalOpen('thirdino')} placeholder="세번째 인플루언서를 선택해주세요."   readOnly/>
              <div style={{ height: '30px' }}></div>
              </div>

              <button type="submit">작성하기</button>
          </form>

        </div>
      </div>


{isModalOpen && (
      <div className="modal">
        <div className="modal-content">
          <form onSubmit={handleSearchSubmit} style={{ height: '240px' }}>
            <p style={{ marginBottom: '10px' }}>인풀루언서 찾아보기</p>
            <p>검색어 없이 검색하시면 모든 인플루언서 List</p>
            <input
              type="text"
              value={searchQuery}
              onChange={handleSearchInputChange}
              placeholder="인플루언서를 입력하세요."
              style={{ marginBottom: '10px' }}
            />
            <button type="submit">검색</button>

          {/* <div className='modal-search'> */}
            {searchResults.map((influencer) => (
              <div
                key={influencer.ino}
                onClick={() => handleInfluencerEdit(selectedField, influencer.ino)}
                // onClick={() => handleInfluencerSelection(selectedField, influencer.ino)}
              >
                {influencer.ino}
                {influencer.name}
              </div>
            ))}
          {/* </div> */}
          </form>
          <div>
            <button onClick={handleModalClose} style={{ float: 'right' }}>닫기</button>
          </div>
        </div>
      </div>
    )}


    </div>
    </div>


    
  );
}

export default RecommendPost;
