FROM node:16-alpine as development

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY package*.json .

# Construir las dependencias para la aplicación.

RUN npm install

COPY . .

RUN npm run build

FROM node:16-alpine as production

ARG NODE_ENV=productiona
ENV NODE_ENV=${NODE_ENV}

WORKDIR /usr/src/app

COPY package*.json .

RUN npm install --only=prod

COPY --from=development /usr/src/app/build ./build
EXPOSE 3000

#Ejecutar la aplicación.
CMD ["npm", "run", "start"]