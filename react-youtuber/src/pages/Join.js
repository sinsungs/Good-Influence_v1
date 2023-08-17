// import React from 'react';
import React, {useState, useEffect} from 'react';
import axios from 'axios';


function Join() {

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
    const serverURL = '/user/join';

    // formData를 서버로 전송합니다.
    axios.post(serverURL, formData)
      .then((response) => {
        console.log(response.data); // 서버 응답 확인
      })
      .catch((error) => {
        console.error(error); // 오류 처리
      });
    }

  return (
    <div>
        <form onSubmit={handleSubmit}>
          <label>
            Email:
            <input type="email" name="email" value={formData.email} onChange={handleChange} />
          </label>
          <br />
          <label>
            Password:
            <input type="password" name="password" value={formData.password} onChange={handleChange} />
          </label>
          <br />
          <label>
            Username:
            <input type="text" name="username" value={formData.username} onChange={handleChange} />
          </label>
          <br />
          <button type="submit">Submit</button>
      </form>

    </div>
  );
}

export default Join;
