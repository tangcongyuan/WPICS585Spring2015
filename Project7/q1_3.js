db.categories2.findOne({children: "dbm"}, {_id: 1});

db.categories2.findOne({children: {$elemMatch: {$eq: "dbm"}}}, {_id: 1});













