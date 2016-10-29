package algorithms.graphs;

/*
 * BFS.java
 *
 * Created on 15 November 2007
 */

import com.yworks.yfiles.algorithms.*;

/**
 * This class provides services that center around breadth first search (BFS).
 *
 * @author Michael A. Bekos
 */

public class BFS
{
    public static NodeList[] getLayers(Graph graph, Node coreNode)
    {
        boolean isdirected = false;
        INodeMap visitingTimes = graph.createNodeMap();
        INodeMap finishingTimes = graph.createNodeMap();
        INodeMap layerIDMap = graph.createNodeMap();
        INodeMap parentNodes = graph.createNodeMap();
        NodeList[] result = BFS.getLayers(graph, coreNode, isdirected, visitingTimes, finishingTimes, layerIDMap, parentNodes);
        graph.disposeNodeMap(visitingTimes);
        graph.disposeNodeMap(finishingTimes);
        graph.disposeNodeMap(layerIDMap);
        graph.disposeNodeMap(parentNodes);
        return result;
    }

    public static NodeList[] getLayers(Graph graph, Node coreNode, INodeMap layerIDMap, INodeMap parentNodes)
    {
        boolean isdirected = false;
        INodeMap visitingTimes = graph.createNodeMap();
        INodeMap finishingTimes = graph.createNodeMap();
        NodeList[] result = BFS.getLayers(graph, coreNode, isdirected, visitingTimes, finishingTimes, layerIDMap, parentNodes);
        graph.disposeNodeMap(visitingTimes);
        graph.disposeNodeMap(finishingTimes);
        return result;
    }

    public static NodeList[] getLayers(Graph graph, Node coreNode, boolean isdirected, INodeMap layerIDMap, INodeMap parentNodes)
    {
        INodeMap visitingTimes = graph.createNodeMap();
        INodeMap finishingTimes = graph.createNodeMap();
        NodeList[] result = BFS.getLayers(graph, coreNode, isdirected, visitingTimes, finishingTimes, layerIDMap, parentNodes);
        graph.disposeNodeMap(visitingTimes);
        graph.disposeNodeMap(finishingTimes);
        return result;
    }

    public static NodeList[] getLayers(Graph graph, Node coreNode, boolean isdirected, INodeMap visitingTimes, INodeMap finishingTimes, INodeMap layerIDMap, INodeMap parentNodes)
    {
        INodeMap nodeColors = graph.createNodeMap();
        for (INodeCursor nc = graph.getNodeCursor(); nc.ok(); nc.next())
        {
            visitingTimes.setInt(nc.node(), -1);
            finishingTimes.setInt(nc.node(), -1);
            layerIDMap.setInt(nc.node(), -1);
            parentNodes.set(nc.node(), null);
            nodeColors.set(nc.node(), java.awt.Color.WHITE);
        }
        int timer = -1;
        visitingTimes.setInt(coreNode, ++timer);
        nodeColors.set(coreNode, java.awt.Color.GRAY);
        layerIDMap.setInt(coreNode, 0);

        //q is a FIFO queue
        java.util.LinkedList<Node> q = new java.util.LinkedList<Node>();
        q.add(coreNode);

        while (!q.isEmpty())
        {
            Node u = q.removeFirst();
            for (INodeCursor nc = (isdirected ? u.getSuccessorCursor() : u.getNeighborCursor()); nc.ok(); nc.next())
            {
                if (nodeColors.get(nc.node()) == java.awt.Color.WHITE)
                {
                    nodeColors.set(nc.node(), java.awt.Color.GRAY);
                    visitingTimes.setInt(nc.node(),  ++timer);
                    layerIDMap.setInt(nc.node(), layerIDMap.getInt(u)+1);
                    parentNodes.set(nc.node(), u);

                    q.add(nc.node());
                }
            }
            nodeColors.set(u, java.awt.Color.BLACK);
            finishingTimes.setInt(u, ++timer);
        }
        graph.disposeNodeMap(nodeColors);

        int maxLayers = 0;
        for (INodeCursor nc = graph.getNodeCursor(); nc.ok(); nc.next())
        {
            maxLayers = Math.max(maxLayers, layerIDMap.getInt(nc.node()));
        }
        NodeList[] result = new NodeList[maxLayers+1];
        for (int i=0; i<result.length; i++)
        {
            result[i] = new NodeList();
        }
        for (INodeCursor nc = graph.getNodeCursor(); nc.ok(); nc.next())
        {
            if (nc.node() == coreNode || parentNodes.get(nc.node())!= null)
            {
                result[layerIDMap.getInt(nc.node())].add(nc.node());
            }
        }
        return result;
    }
}

