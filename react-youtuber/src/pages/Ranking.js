// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';
import axios from 'axios';


function Ranking() {

  const [meetRanks, setMeetRanks] = useState([]);
  const [recommendRank, setRecommendRank] = useState([]);

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/statistics/ranking')
        .then(response => {
      // 데이터를 두 개의 배열로 분리
          setMeetRanks(response.data.meetRanks)
          setRecommendRank(response.data.recommendRank)
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }, []);


return (
    <div className="App">
        <div className='back'>
            <div className="container">

                <ul>
    <div className='rank-container'>
                    <li class="flex-container">
                      <h1>소셜 모임 랭킹</h1>
                    </li>
                    <hr/>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>순위</p>
                            </div>
                            <div class="flex-item-center">
                                <p>유저 정보</p> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>신청 여부</p>
                                </div>
                            </div>
                    </li>
                    <hr/>
                

                    {meetRanks.map((meetRanks, index) => (
                    <div>
                    <li class="flex-container" key={meetRanks.id}>
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{index + 1}</p>
                                <p></p>
                            </div>
                            <div class="flex-item-center">
                                <h3>{meetRanks.username}</h3>
                                <span>{meetRanks.sns}</span> 
                                <span>{meetRanks.experience}</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    {/* <p>{rank.result}</p> */}
                                    <button>신청하기</button>
                                </div>
                            </div>
                    </li>
                    <hr/>

                    </div>
))}


                    <li class="flex-container">
                      <h1>인플루언서 추천 랭킹</h1>
                    </li>
                    <hr/>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>순위</p>
                            </div>
                            <div class="flex-item-center">
                                <p>유저 정보</p> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>신청 여부</p>
                                </div>
                            </div>
                    </li>
                    <hr/>

                    {recommendRank.map((recommendRank, index) => (
                    <div>
                    <li class="flex-container" key={recommendRank.id}>
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{index + 1}</p>
                                <p></p>
                            </div>
                            <div class="flex-item-center">
                                <h3>{recommendRank.name}</h3>
                                <span>{recommendRank.content}</span> 
                                <span>{recommendRank.category}</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    {/* <p>{rank.result}</p> */}
                                    <button>신청하기</button>
                                </div>
                            </div>
                    </li>
                    <hr/>

                    </div>
))}
    </div>
                </ul>

            </div>
        </div>
    </div>
);
}

export default Ranking;
