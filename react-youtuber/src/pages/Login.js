import React, { useState } from 'react';
import axios from 'axios';
import kakao_login_button from '../img/kakao_login_button.png';

function Login() {
  // const [username, setUsername] = useState('');
  // const [password, setPassword] = useState('');
  // const [email, setEmail] = useState('');

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });
  const [jwtToken, setJwtToken] = useState('');


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // POST 요청을 보낼 서버의 엔드포인트 URL을 입력합니다.
    const serverURL = '/user/jwtlogin';

    // formData를 서버로 전송합니다.
    axios.post(serverURL, formData)
      .then((response) => {
        console.log(response.data); // 서버 응답 확인
        setJwtToken(response.data);

        // Axios의 설정을 통해 JWT 토큰을 헤더에 저장합니다.
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;

        // 요청이 성공하면 서버로부터 받은 JWT 토큰을 쿠키에 저장합니다.
        document.cookie = `jwtToken=${response.data};`;
        

      })
      .catch((error) => {
        console.error(error); // 오류 처리
      });
    }
  
  return (
    <div className="App">
      <div className='back'>
      <div className="container">

      <div  style={{ display: 'flex', justifyContent: 'center' , width: '100%' }}>

          <div style={{ width: '300px', height: '800px'  }}>

          <div style={{ textAlign: 'center', margin: '50px 0'}}>
            <h1> 로그인 </h1>
            <p>다양한 인플루언서를 만나보세요</p>
          </div>
            
          
          <form onSubmit={handleSubmit}>
            <label>이메일<br/></label>
              <input type="email" className="custom-input" name="email" value={formData.email} onChange={handleChange} />
            <br />

            <label>비밀번호<br/></label>
              <input type="password"className="custom-input"  name="password" value={formData.password} onChange={handleChange} />
            <br />

            <button type="submit">로그인</button>
        </form>

        <div style={{ textAlign: 'center', margin: '50px 0'}}>
            <p>간편 로그인</p>

            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b58919f7c93ec635d5c0b3697d4aac6b&redirect_uri=http://localhost:3000/login/oauth2/callback/kakao">
            <img src={kakao_login_button} alt='kakao_image' /></a>

        </div>

        </div>
      </div>
      </div>
      </div>
      </div>


  );
}

export default Login;
