// import React, { useState } from 'react';
import axios from 'axios';
import React, {useState, useEffect} from 'react';

function YoutuberPost() {

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [youtubers, setYoutubers] = useState([]);
  const [value, setValue] = useState('');

  const handleRectangleClick = () => {
    setIsModalOpen(true);
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  const handleSearchInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    // 검색 실행 로직 구현
    console.log('Search query:', searchQuery);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
  
    // Prepare the data to be sent
    const postData = {
      title: event.target.elements.title.value,
      content: event.target.elements.content.value,
      yno: event.target.elements.yno.value,
    };
  
    // Send the data to the server
    axios.post('/post/register', postData)
      .then(response => {
        // Handle the server response if needed
        console.log('Server response:', response.data);
        alert('postyoutuber 생성.', response.data);
      })
      .catch(error => {
        // Handle any errors that occurred during the request
        console.error('Error:', error);
      });



      // useEffect(() => {
      //   // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
      //   axios.get('/post/list')
      //     .then(response => {
      //       // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
      //       setYoutubers(response.data);
      //     })
      //     .catch(error => {
      //       // 오류 처리 로직을 작성하세요.
      //       console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      //     });
      // }, []);
  };

  return (
    
    <div className="App">
      <div className="container">
        <div className="rectangle" onClick={handleRectangleClick}>
          {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
        </div>
        <div className="rectangle" onClick={handleRectangleClick}>
          {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
        </div>
        <div className="rectangle" onClick={handleRectangleClick}>
          {/* <img src={rectangleImage} alt="Rectangle" className="rectangle-image" /> */}
        </div>


          <form onSubmit={handleSubmit}>
            <input type="text" name="title" placeholder="Enter title" />
            <input type="text" name="content" placeholder="Enter content" />
            <input type="text" name="yno" placeholder="Enter yno" />
            <button type="submit">Submit</button>
          </form>


      </div>

      {isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <form onSubmit={handleSearchSubmit}>
                <input
                  type="text"
                  value={searchQuery}
                  onChange={handleSearchInputChange}
                  placeholder="Enter your search query"
                />
                <button type="submit">유튜버 검색</button>
              </form>
              <button onClick={handleModalClose}>Close</button>
          </div>
        </div>
      )}

    </div>

    
  );
}

export default YoutuberPost;
