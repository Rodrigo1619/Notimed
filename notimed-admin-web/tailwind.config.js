module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    container: {
      center: true,
    },
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
        "outline": "#75767f",
        'primaryState': {
          'hover': 'rgba(42,86,200,0.08)',
          'focus': 'rgba(42,86,200,0.12)',
          'dragged': 'rgba(42,86,200,0.16)'
        },
        'onPrimaryState': {
          'hover': 'rgba(255, 255, 255, 0.08)',
          'focus': 'rgba(255,255,255,0.12)',
          'dragged': 'rgba(255,255,255,0.16)'
        },
        'primaryContainerState': {
          'hover': 'rgba(218, 225, 255, 0.08)',
          'focus': 'rgba(218, 225, 255, 0.12)',
          'dragged': 'rgba(218, 225, 255, 0.16)'
        },
        'onPrimaryContainerState': {
          'hover': 'rgba(0, 21, 79, 0.08)',
          'focus': 'rgba(0, 21, 79, 0.12)',
          'dragged': 'rgba(0, 21, 79, 0.16)'
        },
        'secondaryState': {
          'hover': 'rgba(89, 94, 113, 0.08)',
          'focus': 'rgba(89, 94, 113, 0.12)',
          'dragged': 'rgba(89, 94, 113, 0.16)'
        },
        'onSecondaryState': {
          'hover': 'rgba(255, 255, 255, 0.08)',
          'focus': 'rgba(255, 255, 255, 0.12)',
          'dragged': 'rgba(255, 255, 255, 0.16)'
        },
        'secondaryContainerState': {
          'hover': 'rgba(221, 225, 249, 0.08)',
          'focus': 'rgba(221, 225, 249, 0.12)',
          'dragged': 'rgba(221, 225, 249, 0.16)'
        },
        'onSecondaryContainerState': {
          'hover': 'rgba(22, 27, 44, 0.08)',
          'focus': 'rgba(22, 27, 44, 0.12)',
          'dragged': 'rgba(22, 27, 44, 0.08)'
        },
        'errorState': {
          'hover': 'rgba(186, 27, 27, 0.08)',
          'focus': 'rgba(186, 27, 27, 0.12)',
          'dragged': 'rgba(186, 27, 27, 0.16)'
        },
        'onErrorState': {
          'hover': 'rgba(255, 255, 255,0.08)',
          'focus': 'rgba(255, 255, 255, 0.12)',
          'dragged': 'rgba(255, 255, 255, 0.16)'
        },
        'errorContainerState': {
          'hover': 'rgba(255, 218, 212,0.08)',
          'focus': 'rgba(255, 218, 212, 0.12)',
          'dragged': 'rgba(255, 218, 212, 0.16)'
        },
        'onErrorContainerState': {
          'hover': 'rgba(65, 0, 1, 0.08)',
          'focus': 'rgba(65, 0, 1, 0.12)',
          'dragged': 'rgba(65, 0, 1, 0.16)'
        },
        'surfaceState': {
          'hover': 'rgba(254, 251, 255, 0.08)',
          'focus': 'rgba(254, 251, 255, 0.12)',
          'dragged': 'rgba(254, 251, 255, 0.16)'
        },
        'onSurfaceState': {
          'hover': 'rgba(27, 27, 31, 0.08)',
          'focus': 'rgba(27, 27, 31, 0.12)',
          'dragged': 'rgba(27, 27, 31, 0.16)'
        },
        'surfaceVariantState': {
          'hover': 'rgba(225, 225, 236, 0.08)',
          'focus': 'rgba(225, 225, 236, 0.12)',
          'dragged': 'rgba(225, 225, 236, 0.16)'
        },
        'onSurfaceVariantState': {
          'hover': 'rgba(69, 70, 79, 0.08)',
          'focus': 'rgba(69, 70, 79, 0.12)',
          'dragged': 'rgba(69, 70, 79, 0.16)'
        },
      },
      fontSize: {
        display1: [
          '64px', {
            letterSpacing: '-0.5px',
            lineHeight: '76px'
          }
        ],
        display2: [
          '57px', {
            letterSpacing: '-0.25px',
            lineHeight: '64px'
          }
        ],
        display3: [
          '45px', {
            letterSpacing: '0px',
            lineHeight: '52px'
          }
        ],
        headline1: [
          '36px', {
            letterSpacing: '0px',
            lineHeight: '44px'
          }
        ],
        headline2: [
          '36px', {
            letterSpacing: '0px',
            lineHeight: '44px'
          }
        ],
        headline3: [
          '28px', {
            letterSpacing: '0px',
            lineHeight: '36px'
          }
        ],
        headline4: [
          '24px', {
            letterSpacing: '0px',
            lineHeight: '32px'
          }
        ],
        headline5: [
          '22px', {
            letterSpacing: '0px',
            lineHeight: '28px'
          }
        ],
        headline6: [
          '18px', {
            letterSpacing: '0px',
            lineHeight: '24px'
          }
        ],
        subhead1: [
            '16px', {
            letterSpacing: '0.1px',
            lineHeight: '24px'
          }
        ],
        subhead2: [
          '14px', {
          letterSpacing: '0.1px',
          lineHeight: '20px'
          }
        ],
        button: [
          '14px', {
          letterSpacing: '0.1px',
          lineHeight: '20px'
          }
        ],
        body1: [
          '16px', {
          letterSpacing: '0.5px',
          lineHeight: '24px'
          }
        ],
        body2: [
          '14px', {
          letterSpacing: '0.25px',
          lineHeight: '20px'
          }
        ],
        caption: [
          '12px', {
          letterSpacing: '0.4px',
          lineHeight: '16px'
          }
        ],
        overline: [
          '12px', {
          letterSpacing: '0.5px',
          lineHeight: '16px'
          }
        ],
        labelSmall: [
          '11px', {
          letterSpacing: '0.5px',
          lineHeight: '16px'
          }
        ],
        displayLarge: [
          '57px', {
          letterSpacing: '-0.25px',
          lineHeight: '64px'
          }
        ],
        displayMedium: [
          '45px', {
            letterSpacing: '0px',
            lineHeight: '52px'
          }
        ],
        displaySmall: [
          '24px', {
            letterSpacing: '0px',
            lineHeight: '32px'
          }
        ],
        headlineLarge: [
          '32px', {
            letterSpacing: '0px',
            lineHeight: '40px'
          }
        ],
        headlineMedium: [
          '28px', {
            letterSpacing: '0px',
            lineHeight: '36px'
          }
        ],
        headlineSmall: [
          '24px', {
            letterSpacing: '0px',
            lineHeight: '32px'
          }
        ],
        titleLarge: [
          '22px', {
            letterSpacing: '0px',
            lineHeight: '28px'
          }
        ],
        titleMedium: [
          '16px', {
            letterSpacing: '0.1px',
            lineHeight: '24px'
          }
        ],
        titleSmall: [
          '14px', {
            letterSpacing: '0.1px',
            lineHeight: '20px'
          }
        ],
        bodyLarge: [
          '16px', {
            letterSpacing: '0.5x',
            lineHeight: '24px'
          }
        ],
        bodyMedium: [
          '14px', {
            letterSpacing: '0.25x',
            lineHeight: '20px'
          }
        ],
        bodySmall: [
          '12px', {
            letterSpacing: '0.4x',
            lineHeight: '16px'
          }
        ],
      },
      boxShadow: {
        'E1': '0px 1px 2px rgba(0, 0, 0, 0.3), 0px 1px 3px 1px rgba(0, 0, 0, 0.15)',
        'E2': '0px 1px 2px rgba(0, 0, 0, 0.3), 0px 2px 6px 2px rgba(0, 0, 0, 0.15)',
        'E3': '0px 1px 2px rgba(0, 0, 0, 0.3), 0px 1px 3px 1px rgba(0, 0, 0, 0.15)',
        'E4': '0px 6px 10px 4px rgba(0, 0, 0, 0.15), 0px 2px 3px rgba(0, 0, 0, 0.3)',
        'E5': '0px 8px 12px 6px rgba(0, 0, 0, 0.15), 0px 4px 4px rgba(0, 0, 0, 0.3)'
      }
    },
  },
  plugins: [],
}