

var item = db.categories2.findOne({children: {$elemMatch: {$eq: "Databases"}}}, {_id: 1});

var item = db.categories2.findOne({_id: item._id}, {_id: 0, children:1});

item.children
















