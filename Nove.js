import {NativeModules} from 'react-native';
const {Nove} = NativeModules;

module.exports = {
  playAudio: function (rate, byteArray) {
    Nove.playAudio(rate, byteArray);
  },
};
