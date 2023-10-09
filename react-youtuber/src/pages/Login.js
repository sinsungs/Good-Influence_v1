import React, { useState } from 'react';
import axios from 'axios';
import kakao_login_button from '../img/kakao_login_button.png';
import { useNavigate } from 'react-router-dom';
import { atom } from 'recoil';

function Login() {
  // const [username, setUsername] = useState('');
  // const [password, setPassword] = useState('');
  // const [email, setEmail] = useState('');

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // 이 로직은 일반 로그인 로직이고 LoginHandeler.js 에서 카카오 로그인 jwt 처리합니다.
    const serverURL = '/api/user/jwtlogin';

    axios.post(serverURL, formData)
      .then((response) => {
        console.log(response.data); // 서버 응답 확인

        if(response.data === '존재하지 않는 아이디 입니다.'){
          alert(response.data);
          return;
        }

        if(response.data === '패스워드를 잘못 입력 했습니다.'){
          alert(response.data);
          return;
        }

        // 요청이 성공하면 서버로부터 받은 JWT 토큰을 쿠키에 저장합니다.
        document.cookie = `jwtToken=${response.data};`;

        // navigate를 사용하여 페이지 이동
        navigate('/meet');
        window.location.reload();

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

            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b58919f7c93ec635d5c0b3697d4aac6b&redirect_uri=https://goodinfluence.shop/login/oauth2/callback/kakao">
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
