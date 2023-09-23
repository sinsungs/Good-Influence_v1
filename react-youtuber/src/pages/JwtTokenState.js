import { atom } from 'recoil';

export const tokenState = atom({
  key: 'tokenState',
  default: document.cookie.replace(/(?:(?:^|.*;\s*)jwtToken\s*=\s*([^;]*).*$)|^.*$/, '$1') || '',
});