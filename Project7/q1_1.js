function findParent(id, output) {
    var temp = db.categories.find({ _id: id}, { _id: 0, parent: 1});
    var parentId = temp[0]["parent"]
    if (parentId) {
        level = level + 1;
        var elem = { Name: parentId, Level: level};
        output.push(elem);
        findParent(parentId, output);
    }   
}

var level = 0;
var output = [];
findParent("MongoDB", output);
output
