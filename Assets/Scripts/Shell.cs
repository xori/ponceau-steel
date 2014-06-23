using UnityEngine;
using System.Collections;

public class Shell : MonoBehaviour {

	public float lifetime = 3;

	private Material wrapper;
	private Color startColour;
	private float deathtime;

	void Start () {
		wrapper = renderer.material;
		startColour = wrapper.color;
		StartCoroutine("Lifetime");
	}

	IEnumerator Lifetime() {
		deathtime = Time.time + lifetime;
		while(true) {
			wrapper.color = Color.Lerp(Color.clear, startColour, (deathtime - Time.time) / lifetime);
			if(Time.time >= deathtime) {
				Destroy(gameObject);
				yield break;
			} else {
				yield return new WaitForSeconds(lifetime / 20f);
			}
		}
	}

	void OnTriggerEnter(Collider coll) {
		if(Time.time > deathtime - lifetime/3 && coll.tag == "Ground") {
			rigidbody.Sleep();
		}
	}
}
