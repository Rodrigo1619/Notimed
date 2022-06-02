module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    fontFamily: {
      sans: ['Raleway', 'sans-serif']
    },
    extend: {
      colors: {
        "primary": "#2a56c8",
        "onPrimary": "#ffffff",
        "primaryContainer": "#dae1ff",
        "onPrimaryContainer": "#00154f",
        "secondary": "#595e71",
        "onSecondary": "#ffffff",
        "secondaryContainer": "#dde1f9",
        "onSecondaryContainer": "#161b2c",
        "tertiary": "#745470",
        "onTertiary": "#ffffff",
        "tertiaryContainer": "#ffd7f8",
        "onTertiaryContainer": "#2c122b",
        "error": "#ba1b1b",
        "onError": "#ffffff",
        "errorContainer": "#ffdad4",
        "onErrorContainer": "#410001",
        "background": "#fefbff",
        "onBackground": "#1b1b1f",
        "surface": "#fefbff",
        "onSurface": "#1b1b1f",
        "surface-variant": "#e1e1ec",
        "onSurface-variant": "#45464f",
        "outline": "#75767f"
      },
      fontSize: {
        display1: [
          '64px', {
            letterSpacing: '-0.5px',
            lineHeight: '76px'
          }
        ]
      },
    },
  },
  plugins: [],
}