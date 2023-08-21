import React, {useState, useEffect} from 'react';
import axios from 'axios';

function YoutuberPost() {

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);


// 모달창 열기 Start 
const handleModalOpen = () => {
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
    const response = await axios.post('/search', { name : searchQuery }); // POST 요청으로 변경
    const data = response.data;
    setSearchResults(data);
  } catch (error) {
    console.error("Error fetching search results:", error);
  }
};
// 게시글 인플루언서 검색 End 


// 게시글 작성 Start
  const handleSubmit = (event) => {
    event.preventDefault();
  
    const postData = {
      title: event.target.elements.title.value,
      content: event.target.elements.content.value,
      yno: event.target.elements.yno.value,
    };
  
    axios.post('/post/register', postData)
      .then(response => {

        console.log('Server response:', response.data);

        alert('추천글 작성을 완료했습니다.', response.data);

        // history.push('/'); // 작성 후 홈으로 이동

      })
      .catch(error => {

        console.error('Error:', error);

      });
  };
// 게시글 작성 End


// 선택한 인플루언서 처리 로직 구현 (예: 상태 업데이트 또는 백엔드로 보내기)
  const handleInfluencerSelection = (influencer) => {

    console.log('Selected influencer:', influencer);
  };

// 선택한 인플루언서 처리 로직 구현 (예: 상태 업데이트 또는 백엔드로 보내기) 

  return (
    
    <div className="App">
      <div className='back'>
      <div className="container">

        <div style={{ width: '100%'}}>

          <h1>행복한 세상을 만들어보아요</h1>
          <p>좋은 영향력을 가지고있는 인플루언서를 추천해주세요</p>

          <form onSubmit={handleSubmit}>
              <label>제목</label>
              <input type="text" className="custom-input" name="title" placeholder="제목을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <label>내용</label>
              <input type="text" className="custom-input" name="content" placeholder="내용을 입력해주세요." />
              <div style={{ height: '30px' }}></div> {/* 마진을 통해 간격 추가 */}

              <label>인플루언서</label>
              <p>* 인플루언서를 1명에서 3명까지 선택할 수 있습니다.</p>
              <input type="text" className="custom-input" name="yno" placeholder="추천 인플루언서를 선택해주세요." />
              <div style={{ height: '30px' }}></div>

              <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                <div className="rectangle" onClick={handleModalOpen}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
                </div>
                <div className="rectangle" onClick={handleModalOpen}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
                </div>
                <div className="rectangle" onClick={handleModalOpen}>
                {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
                </div>
              </div>


              <button type="submit">작성하기</button>
          </form>

        </div>
      </div>

      {/* {isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <form onSubmit={handleSearchSubmit} style={{height : '240px'}}>
                <p style={{marginBottom : '10px'}}>인풀루언서 등록하기</p>
                <input
                  type="text"
                  value={searchQuery}
                  onChange={handleSearchInputChange}
                  placeholder="인플루언서를 입력하세요."
                  style={{marginBottom : '10px'}}
                />
                <button type="submit">검색</button>
              <div className='modal-search'>

              </div>
              </form>
              <div>
                <button onClick={handleModalClose} style={{float : 'right'}}>닫기</button>
              </div>
          </div>
        </div>
      )} */}


{isModalOpen && (
      <div className="modal">
        <div className="modal-content">
          <form onSubmit={handleSearchSubmit} style={{ height: '240px' }}>
            <p style={{ marginBottom: '10px' }}>인풀루언서 등록하기</p>
            <input
              type="text"
              value={searchQuery}
              onChange={handleSearchInputChange}
              placeholder="인플루언서를 입력하세요."
              style={{ marginBottom: '10px' }}
            />
            <button type="submit">검색</button>
          </form>
          <div className='modal-search'>
            {searchResults.map((influencer) => (
              <div
                key={influencer.id}
                onClick={() => handleInfluencerSelection(influencer)}
              >
                {influencer.name}
              </div>
            ))}
          </div>
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

export default YoutuberPost;
