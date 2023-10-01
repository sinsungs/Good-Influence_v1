import { atom } from 'recoil';

export const tokenState = atom({
  key: 'tokenState',
  default: document.cookie.replace(/(?:(?:^|.*;\s*)jwtToken\s*=\s*([^;]*).*$)|^.*$/, '$1') || '',
});

// export function onFileUpload(e) {
//   const ACCESS_KEY = 'AKIAXTUMJ6YERNKOJEMQ';
//   const SECRET_ACCESS_KEY = 'G50fB1wc33frOO7xPtCcRik/7TjkX6W+V1DMJo/U';
//   const REGION = "ap-northeast-2";
//   const S3_BUCKET = 'sinsung-s3';

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