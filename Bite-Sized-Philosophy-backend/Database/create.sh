#!/bin/zsh
export PGPASSWORD='postgres1'
export PGPORT=15432
BASEDIR=$(dirname $0)
DATABASE=philosopher_db
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/philosopher_script.sql"