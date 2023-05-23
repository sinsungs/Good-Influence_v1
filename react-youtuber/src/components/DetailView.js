import React from 'react';

const DetailView = ({ dto }) => {
  return (
    <div>
      <h1>Detail View</h1>
      <div>
        <label>Rno</label>
        <input type="text" value={dto.rno} readOnly />
      </div>
      <div>
        <label>Title</label>
        <input type="text" value={dto.title} readOnly />
      </div>
      <div>
        <label>Content</label>
        <textarea rows="5" value={dto.content} readOnly />
      </div>
      <div>
        <label>Writer</label>
        <input type="text" value={dto.writerName} readOnly />
      </div>
    </div>
  );
};

export default DetailView;