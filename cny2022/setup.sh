#!/usr/bin/env bash
sed -i "s/^:80/:${PORT}/" /etc/caddy/Caddyfile && \
	caddy run --config /etc/caddy/Caddyfile --adapter caddyfile
