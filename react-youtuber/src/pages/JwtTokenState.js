import { atom } from 'recoil';

export const tokenState = atom({
  key: 'tokenState',
  default: document.cookie.replace(/(?:(?:^|.*;\s*)jwtToken\s*=\s*([^;]*).*$)|^.*$/, '$1') || '',
});

// export function onFileUpload(e) {
//   const ACCESS_KEY = '';
//   const SECRET_ACCESS_KEY = '';
//   const REGION = "";
//   const S3_BUCKET = '';

//    // AWS ACCESS KEY를 세팅합니다.
//    AWS.config.update({
//     accessKeyId: ACCESS_KEY,
//     secretAccessKey: SECRET_ACCESS_KEY
//   });

//     // 버킷에 맞는 이름과 리전을 설정합니다.
//     const myBucket = new AWS.S3({
//       params: { Bucket: S3_BUCKET},
//       region: REGION,
//     });

//     // 파일과 파일이름을 넘겨주면 됩니다.
//     const params = {
//       ACL: 'public-read',
//       Body: file,
//       Bucket: S3_BUCKET,
//       Key: file.name
//     };

//     myBucket.putObject(params)
//     .on('httpUploadProgress', (evt) => {
//       alert("SUCCESS")
//     })
//     .send((err) => {
//       if (err) console.log(err)
//     })
//   }