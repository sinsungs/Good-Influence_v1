import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function LoginHandler() {

  const code = new URL(window.location.href).searchParams.get("code");
  // const [jwtToken, setJwtToken] = useState('');
  console.log(code);
  const navigate = useNavigate();

  useEffect(() => {
    // GET 요청을 보낼 서버의 엔드포인트 URL을 입력합니다.
    const serverURL = '/auth/kakao/callback';

    // GET 요청으로 보낼 데이터를 객체로 생성합니다.
    const requestData = {
      code: code,
      // 다른 필요한 데이터를 추가할 수 있습니다.
    };

    // GET 요청을 보냅니다.
    axios.get(serverURL, {
      params: requestData, // 데이터를 쿼리 매개변수로 전달합니다.
    })
      .then((response) => {
        console.log(response.data); // 서버 응답 확인
        // setJwtToken(response.data);

        // Axios의 설정을 통해 JWT 토큰을 헤더에 저장합니다.
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;

        // 요청이 성공하면 서버로부터 받은 JWT 토큰을 쿠키에 저장합니다.
        document.cookie = `jwtToken=${response.data};`;

        // window.location.href = '/meeting';
          // navigate를 사용하여 페이지 이동
          // navigate('/meeting');


      })
      .catch((error) => {
        console.error(error); // 오류 처리
      });

  }, []);

  return (
    <div className="LoginHandler">
      <div className="notice">
        <p>로그인 중입니다.</p>
        <p>잠시만 기다려주세요.</p>
        <div className="spinner"></div>
      </div>
    </div>
  );
}

export default LoginHandler;