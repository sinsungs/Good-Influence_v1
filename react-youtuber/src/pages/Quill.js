// import React from 'react';
import React, {useState, useEffect} from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function Quill() {

    const [value, setValue] = useState('');

  return (
    <div>
        <ReactQuill theme="snow" value={value} onChange={setValue} />;
    </div>
  );
}

export default Quill;
