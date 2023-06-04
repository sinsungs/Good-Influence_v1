
// import PostList from './PostList';
// import PostDetail from './PostDetail';
// import NotFound from './NotFound';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Youtuber from './pages/Youtuber';

function App() {
  return (
    <Router>
      <div>
        <Header />
        <Routes>
          {/* <Route exact path="/" component={PostList} />
          <Route path="/post/:id" component={PostDetail} />
          <Route component={NotFound} /> */}
        </Routes>
        <Footer />
        <Youtuber/>
      </div>
    </Router>
  );
}

export default App;