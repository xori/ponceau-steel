using UnityEngine;
using System.Collections;

public class Weapon : MonoBehaviour {

	public enum WeaponType {Semi, Auto, Burst};
	public WeaponType type = WeaponType.Semi;
	public float firerate; // per sec
	public float lastfire;
	public float damage;

	public Transform spawn;
	public LineRenderer tracer;
	public Transform shellEjection;
	public Rigidbody shell;

	public LayerMask squishies;

	public void Start() {
		squishies = new LayerMask();
		if(GetComponent<LineRenderer>()) {
			tracer = GetComponent<LineRenderer>();
		}
	}

	public Weapon () {
		lastfire = 0;
		firerate = 1;
	}

	public void Shoot() {
		if(lastfire > Time.time ) {
			return; // too hot.
		}
		Ray ray = new Ray(spawn.position, spawn.forward);
		RaycastHit hit;
		var distance = 20f;

		if(Physics.Raycast(ray, out hit)) {
			distance = hit.distance;
			if(hit.collider.GetComponent<SquishyEntity>()){
				hit.collider.GetComponent<SquishyEntity>().Damage(damage);
			}
		}
		lastfire = Time.time + firerate;
		Debug.DrawRay(ray.origin, ray.direction * distance, Color.Lerp(Color.green, Color.red, distance / 20), 1);
		if(tracer) {
			StartCoroutine("ShowTracer", ray.direction * distance);
		}
		if(shellEjection && shell) {
			Rigidbody entity = Instantiate(shell, shellEjection.position, Quaternion.identity) as Rigidbody;
			entity.AddForce(shellEjection.forward * Random.Range(150, 200) + spawn.forward * Random.Range(-15,15));
		}
	}

	IEnumerator ShowTracer(Vector3 endPosition) {
		tracer.enabled = true;
		tracer.SetPosition(0, spawn.position);
		tracer.SetPosition(1, spawn.position + endPosition);
		yield return null;
		tracer.enabled = false;
	}
}
