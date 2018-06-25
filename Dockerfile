FROM python:3.7-rc

LABEL version="1.0"
EXPOSE 5000
ADD greetings_app/ /greetings_app/
RUN pip install -r /greetings_app/requirements.txt

ENV DB_URL=sqlite:///foo.db

ENTRYPOINT python3 greetings_app/app.py
