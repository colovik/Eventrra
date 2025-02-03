db = db.getSiblingDB('admin');

db.createUser({
    user: 'colovik',
    pwd: 'admin',
    roles: [{role: 'root', db: 'admin'}]
});


db = db.getSiblingDB('eventrraDB');
db.createUser({
    user: 'colovik',
    pwd: 'admin',
    roles: [{role: 'readWrite', db: 'eventrraDB'}]
});
