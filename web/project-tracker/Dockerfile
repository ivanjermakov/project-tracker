FROM node:latest
RUN mkdir -p /app
WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY . /app/
EXPOSE 4200
#CMD ["npm", "start", "--host 0.0.0.0", "--disable-host-check"]
