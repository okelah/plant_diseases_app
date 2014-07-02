package plant.diseases.android.entity;

/**
 * 病害诊断条目Entity<br/>
 * Created by Timothy on 2014/6/28.
 */
public class DiseaseItem {
	/** item id */
	public int itemId;
	/** parent item id */
	public int parentId;
	/** item text */
	public String itemText;
	/** 是否为叶子节点 */
	public boolean leafFlag;

	public DiseaseItem(int itemId, int parentId, String itemText, boolean leafFlag) {
		this.itemId = itemId;
		this.parentId = parentId;
		this.itemText = itemText;
		this.leafFlag = leafFlag;
	}
}
