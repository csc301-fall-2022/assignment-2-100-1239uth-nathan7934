# pull official base image
FROM node:18.9

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json .
COPY yarn.lock .
RUN yarn install
RUN yarn add react-scripts

# add app
COPY . .

# start app
CMD ["yarn", "build"]