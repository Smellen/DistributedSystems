README

Ellen Burke
10319941

Assumptions I made:
All nodes are on a LAN. So all of their IPs are localhost. IP address of each of my nodes isn't used because they are all the same. I look for the port numbers instead.

Ports start from 8767. Ports incremented after each node joins the network so that each node has it's own port number.

Working Parts:

Packets - I changed the packet format from the spec. Instead of sending JSON format packets I created a Packet class. A packet object will have attributes of:
	packet type;
	targetID
	senderID
	gatewayID
	nodeID
	ip
	keyword
	word being searched
	urls array
	search_response with all URLS
	routing table.
Each packet is then turned into a string. Each attribute of the packet is divided by a comma. When I parse the string after it has been sent I parse it with the comma.
I stuck with the necessary information being sent in each packet so that it was the same information being sent if it was using JSON.


BootStrap Node - I initialise the first node as the bootstrap. It's keyword is "bootstrap" and I hash the keyword to get the nodeID. The hash of the bootstrap is 1994011026. All packets go through the bootstrap. For my routing the bootstrap is also the only node that has a complete routing table. I ran out of time and didn't get to finish added a correct routing table to all other nodes in the network.

Index
For index I created packet that was for the type INDEXING. When it arrives at a node I parse the string and compare the targetID to the current nodeID. When they match it means the packet has arrived at the node that it wants. From there it takes the url that is to be indexed and adds it to it's list of other URLs. Once the URL has been indexed I send another packet back to the send to acknowledge that the index has happened. This is the ACK_INDEX packet.

Search & Search_Response
Searching is similar to index. A packet arrives at a node and the node checks that it is the targetID. If it is then a new packet is created. The new packet is created with the URLs and frequency added into it. This packet is then sent back to the node that requested the search. When a node receives a search_response packet it parses the packet and prints out the URLs received from the sender node.

Leaving
When a node leaves the network it sends a leaving packet to the bootstrap

Joining
When a node joins the network is send a joining message to the bootstrap node. The node then adds the joining nodes port to it's routing table. Again all nodes are local host so I couldn't use IPs.


PING & ACK
When a node receives a PING packet it checks that it's the target node. If it is then the node sends a reply of an ACK back to the sender to acknowledge the ping. 



Things that don't work perfectly but have been attempted

Joining Network Relay
I'm not sure what this relay was supposed to do so I left it out.

Routing
My bootstrap node is the only node with a complete routing table. I ran out of time but if I had the time I would have gotten the bootstrap to send out a node to all the nodes in the network about each new node that has joined and each node that leaves.


BootStrap Node
I start off with a bootstrap node with the port 8767. It is the only node with a complete routing table.







