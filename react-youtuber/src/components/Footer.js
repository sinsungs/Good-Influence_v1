import React from 'react';
import { Link } from 'react-router-dom';

function Footer(){
    return(
        <div id="subFooter" class="container">
            <div class="container_inner">
                <div>
                    <ul>
                        {/* <li><Link to="/planner"><img src="/img/home.png" alt="home"/></Link></li>
                        <li><Link to="/board"><img src="/img/list.png" alt="list"/></Link></li>
                        <li><Link to="/challenge"><img src="/img/flag.png" alt="flag"/></Link></li>
                        <li><Link to="/profile"><img src="/img/user.png" alt="user"/></Link></li> */}

                        <li>
                            <p>(주)영진상사 | 010-1234-1234</p>
                            <p>대구광역시 북구 복현로 35</p>
                            <p>사업자 등록번호 : 000-0000</p>
                            <p>통신판매업신고 : 0000-xxxx-000000</p>
                            <p>대표 : 심상희 | 개인정보책임자 : 이민화</p>
                            <p>이메일 : yeungjin@naver.com</p>
                            <br/>
                            <p>copyright 2023. yeungjinsangsa. All rights reserved.</p>
                        </li>
                        <li className="social_area">
                            <img src="/img/insta.png" alt="insta" />
                            <img src="/img/fb.png" alt="fb" />
                            <img src="/img/nv.png" alt="nv" />
                        </li>
                        <li>
                            <p>제휴 챌린지 문의</p>
                            <p>조직문화 챌린지 문의</p>
                            <p>랜선 대회 문의</p>
                            <p>챌스토어 신규입점 문의</p>
                            <p>챌스토어</p>
                            <p>이용약관</p>
                            <p>개인정보 처리방침</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default Footer;