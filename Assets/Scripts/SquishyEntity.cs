using UnityEngine;
using System.Collections;

public class SquishyEntity : MonoBehaviour {
	public float health = 50;

	public virtual void Damage(float amt) {
		health -= amt;
		if(health <= 0) Die ("Low Health");
	}

	public virtual void Die(string reason) {
		Destroy(gameObject);
		Debug.Log(this.name + " died due to " + reason);
	}
}
