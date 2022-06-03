module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    fontFamily:{
      raleway: ['Raleway', 'sans-serif']  
    },
    colors: {
      'blue-primary': '#2A56C8',
      'gray-background': '#E1E1EC',
      'gray-text': '#45464F',
      'surface': '#FEFBFF',
      'light-blue': '#B4C5FF',
      'primary-container': '#003BAF',
    },
    screens: {
      sm: '480px',
      md: '768px',
      lg: '976px',
      xl: '1440px',
    },
    extend: {}
  },
  plugins: [],
}