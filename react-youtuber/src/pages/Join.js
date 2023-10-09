// import React from 'react';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import kakao_login_button from '../img/kakao_login_button.png';
import { useNavigate } from 'react-router-dom';


function Join() {

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
    username: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // POST 요청을 보낼 서버의 엔드포인트 URL을 입력합니다.
    const serverURL = '/api/user/join';

    // formData를 서버로 전송합니다.
    axios.post(serverURL, formData)
      .then((response) => {
        console.log(response.data); // 서버 응답 확인
        alert(response.data)
      // navigate를 사용하여 페이지 이동

      if(response.data === '회원가입에 성공하셨습니다.'){
        navigate('/login');
      }

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
            <h1> 회원가입 </h1>
            <p>다양한 인플루언서를 만나보세요</p>
          </div>
            
          
          <form onSubmit={handleSubmit}>
            <label>이메일<br/></label>
              <input type="email" className="custom-input" name="email" value={formData.email} onChange={handleChange} />
            <br />

            <label>비밀번호<br/></label>
              <input type="password"className="custom-input"  name="password" value={formData.password} onChange={handleChange} />
            <br />

            <label>비밀번호 확인<br/></label>
              <input type="password" className="custom-input" name="password" value={formData.password} onChange={handleChange} />
            <br />
            
            <label>닉네임<br/></label>
              <input type="text" className="custom-input" name="username" value={formData.username} onChange={handleChange} />

            <br />
            <button type="submit">회원가입</button>
        </form>

        <div style={{ textAlign: 'center', margin: '50px 0'}}>
            <p>간편 회원가입</p>

            <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b58919f7c93ec635d5c0b3697d4aac6b&redirect_uri=http://localhost:8080/auth/kakao/callback">
            <img src={kakao_login_button} alt='kakao_image' /></a>
        </div>

        </div>
      </div>
      </div>
      </div>
      </div>


  );
}

export default Join;
