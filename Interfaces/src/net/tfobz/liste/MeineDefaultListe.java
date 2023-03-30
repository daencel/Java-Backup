package net.tfobz.liste;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class MeineDefaultListe implements MeineListe {

	private ListenElement erstesElem = null;

	// Lokale Klasse
	class ListenElement extends Object {
		private Object element = null;
		private ListenElement naechstesElem = null;

		private ListenElement(Object element, ListenElement naechstesElem) {
			this.element = element;
			this.naechstesElem = naechstesElem;
		}
	}

	public boolean einfuegenErstesElement(Object element) {
		boolean ret = false;
		if (element != null) {
			ret = true;
			this.erstesElem = new ListenElement(element, erstesElem);
		}
		return ret;
	}

	public boolean loeschenErstesElement() {
		boolean ret = false;
		if (erstesElem != null) {
			ret = true;
			this.erstesElem = this.erstesElem.naechstesElem;
		}
		return ret;
	}

	public boolean istLeer() {
		return this.erstesElem == null;
	}

	public void leeren() {
		this.erstesElem = null;
	}

	public MeinIterator elemente() {

		return new MeinIterator() {

			public ListenElement aktuellesElem = null;

			public boolean hatNaechstesElement() {
				boolean ret = false;
				if (this.aktuellesElem == null)
					ret = MeineDefaultListe.this.erstesElem != null;
				else
					ret = this.aktuellesElem.naechstesElem != null;
				return ret;
			}

			public Object naechstesElement() {
				Object ret = null;
				if (this.aktuellesElem == null) {
					if (MeineDefaultListe.this.erstesElem != null) {
						this.aktuellesElem = MeineDefaultListe.this.erstesElem;
						ret = this.aktuellesElem.element;
					}
				} else if (this.aktuellesElem.naechstesElem == null) {
					this.aktuellesElem = erstesElem;
					ret = this.aktuellesElem.element;
				} else {
					this.aktuellesElem = this.aktuellesElem.naechstesElem;
					ret = this.aktuellesElem.element;
				}
				return ret;
			}

			public boolean einfuegenElement(Object element) {
				boolean ret = false;
				if (element != null) {
					ret = true;
					if (this.aktuellesElem == null) {
						MeineDefaultListe.this.erstesElem = new ListenElement(element,
								MeineDefaultListe.this.erstesElem);
						this.aktuellesElem = MeineDefaultListe.this.erstesElem;
					} else {
						this.aktuellesElem.naechstesElem = new ListenElement(element, this.aktuellesElem.naechstesElem);
						this.aktuellesElem = this.aktuellesElem.naechstesElem;
					}
				}
				return ret;
			}

			/**
			 * Setzt das Element auf welchem momentan der Iterator zeigt, indem es das in
			 * der Liste vorhandene Element mit dem übergebenen Element ersetzt. Die
			 * Position des Iterators wird dabei nicht verändert.
			 * 
			 * @param element das zu setzen ist
			 * @return false falls das übergebene Element null ist oder kein aktuelles
			 *         Element angesprungen wurde
			 */
			public boolean setzenAktuellesElement(Object element) {
				boolean ret = false;
				if (element != null && this.aktuellesElem != null) {
					ret = true;
					this.aktuellesElem.element = element;
				}
				return ret;
			}

			/**
			 * Löscht das Element, welches momentan das aktuelle Element des Iterators ist.
			 * Beim Löschen des aktuellen Elementes wird als neues aktuelles Element jenes
			 * Element genommen, welches nach dem zu löschenden Element steht. Dabei wird
			 * von dieser Methode der Iterator so gesetzt, dass der nachfolgende Aufruf von
			 * naechstesElement() dieses Element zurückliefert.
			 * 
			 * @return false falls es noch kein aktuelles Element gibt, das gelöscht werden
			 *         könnte
			 */
			@Override
			public boolean loeschenAktuellesElement() {
				boolean ret = false;
				boolean ret2 = false;
				if (this.aktuellesElem != null && aktuellesElem != null) {
					Object ele = this.aktuellesElem.element;
					while (!ret) {
						Object elem = null;
						if (this.aktuellesElem.naechstesElem != null) {
							elem = this.aktuellesElem.naechstesElem.element;
						}
						if (elem != null) {
							boolean ele1 = ((Auto) ele).equals((Auto) elem);
							if (ele1) {
								this.aktuellesElem.naechstesElem = this.aktuellesElem.naechstesElem.naechstesElem;
								ret = true;
							}
							this.aktuellesElem = this.aktuellesElem.naechstesElem;
						} else if (ret2) {
							ret = true;
							this.aktuellesElem = MeineDefaultListe.this.erstesElem;
							MeineDefaultListe.this.erstesElem = this.aktuellesElem.naechstesElem;
						} else {
							ret2 = true;
							this.aktuellesElem = MeineDefaultListe.this.erstesElem;
						}

					}
					return true;
				}
				return false;
			}
		};
	}
}