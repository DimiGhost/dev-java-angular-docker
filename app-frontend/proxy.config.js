const proxy = [
  {
    context: '/api',
    secure: false,
    target: 'http://localhost:8080',
    pathRewrite: {'^/api' : ''}
  }
];
module.exports = proxy;