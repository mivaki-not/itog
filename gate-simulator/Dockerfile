FROM node:14-slim
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY app.js data.json ./
CMD ["npm", "start"]
EXPOSE 9999