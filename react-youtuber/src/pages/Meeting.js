// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';
import axios from 'axios';

function Meeting() {


    const [meets, setMeets] = useState([]);

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/meet/list')
        .then(response => {
            setMeets(response.data);
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


            
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>모임 시간</p>
                            </div>
                            <div class="flex-item-center">
                                <p>모임 내용</p> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>신청 여부</p>
                                </div>
                            </div>
                    </li>
                    <hr/>

                    {meets.map(meets => (
                    <div>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{meets.meettime}</p>
                            </div>
                            <div class="flex-item-center">
                                <h3>{meets.title}</h3>
                                <span>{meets.content}</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>{meets.result}</p>
                                </div>
                            </div>
                    </li>
                    <hr/>

                    </div>



))}
                </ul>
            </div>
        </div>
    </div>
);
}

export default Meeting;
