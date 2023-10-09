import React, { useState, useEffect } from 'react';
import image1 from '../img/react.webp';
import image2 from '../img/youtuber.jpg';
import image3 from '../img/instagram.jpg';



const Banner = () => {
  const images = [image1, image2, image3];

  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 3000); // 3초마다 이미지 변경

    return () => {
      clearInterval(timer);
    };
  }, [images.length]);

  return (
    <div className="banner">
      <img src={images[currentIndex]} alt={`Slide ${currentIndex}`} className="banner_image" />
    </div>
  );
};

export default Banner;