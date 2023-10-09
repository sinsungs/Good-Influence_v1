// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';
import axios from 'axios';

function Admin() {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/api/admin/user')
        .then(response => {
            setUsers(response.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }, []);


        const handleDeleteUser = (userId) => {

            const confirmDelete = window.confirm("유저를 영구삭제 하시겠습니까 ?");

            if (confirmDelete) {
                
            axios.delete(`/api/admin/delete/${userId}`)
            .then(response => {

                alert('삭제 성공했습니다.')
                window.location.reload();
                
            })
            .catch(error => {
                console.error('Error deleting user:', error);
            });
        }   else {
            // User canceled, do nothing
        }
        
        };
        

        const handleEditUser = (userId) => {

            const confirmDelete = window.confirm("유저를 인플루언서로 등록 하시겠습니까 ?");

            if (confirmDelete) {
                
            axios.patch(`/api/admin/verify/${userId}`)
            .then(response => {

                alert('인플루언서로 설정 했습니다.')

                window.location.reload();
                
            })
            .catch(error => {
                console.error('Error deleting user:', error);
            });
        }   else {
            // User canceled, do nothing
        }

        };



return (
    <div className="App">
        <div className='back'>
            <div className="container">

                <ul>
                    <hr/>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>가입 시간</p>
                            </div>
                            <div class="flex-item-center">
                                <h3>유저 정보</h3>
  
                            </div> 
                            <div class="flex-item-right">
                                {/* <div class="full"> */}
                                    <p>기능</p>
                                {/* </div> */}
                            </div>
                        {/* </a>  */}
                    </li>
                    <hr/>

                    {users && users.map(user => (
                        <div key={user.id}>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{user.regdate}</p><br/>
                                {/* <p>{user.id}</p> */}
                            </div>
                            <div class="flex-item-center">
                                <span>{user.username}</span><br/>
                                <span>{user.email}</span><br/>
                                <span>{user.password}</span><br/>
                            

                            </div> 
                            <div class="flex-item-right">
                                {/* <div class="full"> */}
                                <span>{user.sns}</span><br/>
                                <button onClick={() => handleEditUser(user.id)}>인플루언서 등록</button>
                                <button onClick={() => handleDeleteUser(user.id)} style={{backgroundColor:'red'}}  >탈퇴</button>
                                </div>
                            {/* </div> */}

                    </li>  
                    <hr/>     
                    </div>

                    ))}


                </ul>

            </div>
        </div>
    </div>
);
}

export default Admin;
