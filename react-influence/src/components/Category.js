// import React from 'react';
import React, {useState, useEffect} from 'react';
import image1 from '../img/category.JPG';
import image2 from '../img/youtuber.jpg';
import image3 from '../img/instagram.jpg';

function Category() {

  const images = [image1, image2, image3];

  const [currentIndex, setCurrentIndex] = useState(0);

  return (
    <div className="category">
      <img src={images[currentIndex]} alt={`Slide ${currentIndex}`} className="category_image" />
    </div>
  );
}

export default Category;
