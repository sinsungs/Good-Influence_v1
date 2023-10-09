// import React from 'react';
import React, {useState, useEffect} from 'react';

function MyPage() {

  return (
    <div className="App">
      <div className='back'>
      <div className="container">

      <div  style={{ display: 'flex', justifyContent: 'center' , width: '100%' }}>

          <div style={{ width: '300px', height: '800px'  }}>

          <div style={{ textAlign: 'center', margin: '50px 0'}}>
            <h1> 마이페이지 </h1>
            <p>다양한 인플루언서를 만나보세요</p>
          </div>
            

            <label>가입일시<br/></label>
              <input type="text" className="custom-input" name="regdate" />
            <br />

            <label>이메일<br/></label>
              <input type="text" className="custom-input" name="email" />
            <br />

            <label>비밀번호<br/></label>
              <input type="text"className="custom-input" value="*******" name="password" />
            <br />

            <label>등급<br/></label>
              <input type="text"className="custom-input"  name="role" />
            <br />

            <label>예치금<br/></label>
              <input type="text"className="custom-input"  name="amount" />
            <br />

            <label>인플루언서 플랫폼<br/></label>
              <input type="text"className="custom-input"  name="sns" />
            <br />



        </div>
      </div>
      </div>
      </div>
      </div>


  );
}

export default MyPage;
