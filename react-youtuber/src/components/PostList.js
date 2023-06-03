import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function PostList() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      const response = await axios.get('/api/posts'); // 서버로부터 게시물 목록 데이터를 가져옴
      setPosts(response.data); // 가져온 데이터를 state에 저장
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <h1>Post List</h1>
      {posts.map(post => (
        <div key={post.id}>
          <Link to={`/post/${post.id}`}>
            <h2>{post.title}</h2>
          </Link>
          <p>{post.body}</p>
        </div>
      ))}
    </div>
  );
}

export default PostList;