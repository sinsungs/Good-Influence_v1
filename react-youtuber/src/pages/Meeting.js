// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';

function Meeting() {
return (
    <div className="App">
        <div className='back'>
            <div className="container">
                <ul>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>18:00</p>
                            </div>
                            <div class="flex-item-center">
                                <h3>서울 영등포 더에프 필드 B구장</h3>
                                <span class="match--option isMix">남녀모두</span> 
                                <span>6vs6</span> <span>3파전</span> 
                                <span>모든 레벨</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>신청가능</p>
                                </div>
                            </div>
                        {/* </a>  */}
                    </li>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>19:00</p>
                            </div>
                            <div class="flex-item-center">
                                <h3>서울 송파 천마 풋살파크 2구장</h3>
                                <span class="match--option isMix">남녀모두</span> 
                                <span>6vs6</span> <span>3파전</span> 
                                <span>모든 레벨</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>마감</p>
                                </div>
                            </div>
                        {/* </a>  */}
                    </li>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>20:00</p>
                            </div>
                            <div class="flex-item-center">
                                <h3>서울 도봉 루다 풋살장 </h3>
                                <span class="match--option isMix">남녀모두</span> 
                                <span>6vs6</span> <span>3파전</span> 
                                <span>모든 레벨</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>마감</p>
                                </div>
                            </div>
                        {/* </a>  */}
                    </li>
                    {/* 다른 리스트 아이템들도 추가 */}
                    <hr/>
                </ul>
            </div>
        </div>
    </div>
);
}

export default Meeting;
