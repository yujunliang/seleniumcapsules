#!/bin/sh
xvfb :88  -screen 0 1024x768x8  &
export DISPLAY=:88
