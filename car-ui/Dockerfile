#FROM nginx:1.17.1-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
#COPY /dist/java-competency-demo-ui /usr/share/nginx/html

# Use the official Node.js image as the base image
FROM node:16 as build

# Set the working directory inside the container
WORKDIR /app

# Add a unique argument to invalidate cache when package.json changes
ARG CACHEBUST=1

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install app dependencies
RUN npm install

# Install the Angular CLI globally
RUN npm install -g @angular/cli

# Copy the rest of the app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Use the official Nginx image as the final base image
FROM nginx:latest

# Copy the built Angular app from the previous stage to the Nginx web server directory
COPY --from=build /app/dist/java-competency-demo-ui /usr/share/nginx/html

# Expose port 80 to access the app
EXPOSE 80

# Start the Nginx web server
CMD ["nginx", "-g", "daemon off;"]