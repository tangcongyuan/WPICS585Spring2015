
function findHeight(id){
    var depth = [];
    var item = db.categories.findOne({_id: id});
    var stack = [];
    stack.push(item);
    while(stack.length > 0){
        depth.push(1);
        var current = stack.pop();
        var children = db.categories.find({parent: current._id});
        while(children.hasNext()){
            var child = children.next();
            depth.push(1 + findHeight(child._id));
            stack.push(child);
        }
    }
    return depth.sort()[depth.length-1];
}

findHeight("Books");





