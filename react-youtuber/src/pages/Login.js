import React, { useState } from 'react';
import axios from 'axios';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const user = {
      username: username,
      password: password,
      email: email
    };

    axios.post('/user/join', user)
      .then(response => {
        console.log(response.data);
        // 로그인 성공 시 처리 로직 추가
      })
      .catch(error => {
        console.error(error);
        // 로그인 실패 시 처리 로직 추가
      });
  };

  return (
    <div>
      <h2>로그인</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>아이디:</label>
          <input type="text" value={username} onChange={handleUsernameChange} required />
        </div>
        <div>
          <label>비밀번호:</label>
          <input type="password" value={password} onChange={handlePasswordChange} required />
        </div>
        <div>
          <label>이메일:</label>
          <input type="email" value={email} onChange={handleEmailChange} required />
        </div>
        <button type="submit">로그인</button>
      </form>
    </div>
  );
}

export default Login;
