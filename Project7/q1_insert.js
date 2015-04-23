db.categories.insert({ _id: "MongoDB", parent: "Databases" });
db.categories.insert({ _id: "dbm", parent: "Databases" });
db.categories.insert({ _id: "Databases", parent: "Programming" });
db.categories.insert({ _id: "Languages", parent: "Programming" });
db.categories.insert({ _id: "Programming", parent: "Books" });
db.categories.insert({ _id: "Books", parent: null });