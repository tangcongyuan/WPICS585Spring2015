function findDescendants(id){
    var item = db.categories2.findOne({_id: id}, {_id: 0, children: 1});
    if(item){
        var children = item.children;
        var len = item.children.length
        for(var i = 0; i < len; i++){
            descendants.push(children[i]);
            findDescendants(children[i]);
        }
    }
}

var descendants = [];
findDescendants("Books")
descendants
























