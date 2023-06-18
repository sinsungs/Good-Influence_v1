import React from 'react';
import '../App.css';

function Youtuber() {

  // constructor(props) {
  //   super(props);
  //   this.state = {
  //     username: '',
  //     email: '',
  //     password: ''
  //   };
  // }

  // handleChange = (event) => {
  //   this.setState({
  //     [event.target.name]: event.target.value
  //   });
  // }

  // handleSubmit = (event) => {
  //   event.preventDefault();
  //   // 등록 로직 작성
  // }
    return (
      <div className="container">
        <h1>유튜버 등록</h1>
        {/* <form onSubmit={this.handleSubmit}> */}
          <label htmlFor="username">사용자 이름:</label>
          <input type="text" id="username" name="username" required />
          
          <label htmlFor="email">이메일:</label>
          <input type="email" id="email" name="email" required />
          
          <label htmlFor="password">비밀번호:</label>
          <input type="password" id="password" name="password" required />
          
          <input type="submit" value="등록" />
        {/* </form> */}
      </div>
    );
  }

export default Youtuber;