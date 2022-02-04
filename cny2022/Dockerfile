FROM node:17 AS ng_build

ARG APP_DIR=/app

WORKDIR ${APP_DIR}

COPY . .

RUN npm i -g typescript @angular/cli
RUN npm ci
RUN ng build

FROM caddy:2

ARG APP_DIR=/app

COPY --from=ng_build ${APP_DIR}/dist/cny2022/ /usr/share/caddy/
ADD setup.sh /setup.sh
RUN chmod a+x /setup.sh

ENV PORT=80

CMD [ "/bin/sh", "/setup.sh" ]
