FROM node:17-alpine as builder
WORKDIR /app
COPY package*.json /app/
RUN npm install --production
COPY .. /app
RUN npm run build

FROM nginx:1.21-alpine
COPY --from=builder /app/build /usr/share/nginx/html
COPY --from=builder /app/nginx.conf /etc/nginx/nginx.app
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]