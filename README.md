# 🌸 Pink Note App

Pink Note, Jetpack Compose ve saf Kotlin kullanılarak geliştirilmiş, modern bir Android not alma uygulamasıdır. Bu proje; harici kütüphaneler kullanmadan **Saf Kotlin ile Dependency Injection (Bağımlılık Enjeksiyonu)** mantığını kavramak, **Room Database** ile yerel veri tabanı yönetimi yapmak ve **Jetpack Navigation** bileşenlerini pratik etmek amacıyla oluşturulmuştur.

---

## 📌 Proje Amacı ve Öğrenilenler
* **Saf Kotlin ile Dependency Injection:** Framework (Dagger/Hilt/Koin) kullanmadan, `ViewModelFactory` ve Repository kalıbıyla bağımlılıkların manuel ve temiz bir şekilde sınıflara nasıl aktarılacağını öğrenmek.
* **Room Database:** Yerel veri tabanı işlemleri (`Entity`, `DAO`, `Database`) ve Coroutines ile asenkron veri akışını yönetmek.
* **Jetpack Compose Navigation:** `NavHost`, `navController` ve dinamik argüman (`noteId`) geçişleriyle ekranlar arası geçişleri kurmak.
* **Smart Screen Reuse (Akıllı Ekran Yeniden Kullanımı):** Aynı ekranı hem yeni kayıt ekleme hem de var olan kaydı güncelleme amacıyla akıllıca ortak kullanmak.

---

## ✨ Öne Çıkan Özellikler

1. **Not Listeleme Ekranı (`NoteScreen`):**
   * Kaydedilen tüm notlar modern kart bileşenleri (`NoteItem`) içinde listelenir.
   * Her kartın üzerinde **Düzenle (Kalem)** ve **Sil (Çöp Kutusu)** ikon butonları yer alır.
   * Sağ alt köşede yeni not eklemek için bir **Floating Action Button (+)** bulunur.

2. **Dinamik Ortak Ekran (`AddNoteScreen`):**
   * Hem yeni not eklemek hem de mevcut notu düzenlemek için **tek bir ekran** kullanılır.
   * Navigasyon sırasında gönderilen `noteId` parametresi kontrol edilir:
     * Eğer `noteId = -1` ise: Sayfa **"New Note"** moduyla açılır, boş alanlar sunulur ve kaydedildiğinde yeni kayıt eklenir (`insert`).
     * Eğer geçerli bir `noteId` geldiyse: Sayfa **"Edit Note"** moduyla açılır, `LaunchedEffect` arka planda ilgili notu bulur ve alanları otomatik doldurur; kaydedildiğinde mevcut kayıt güncellenir (`update`).

---

## 🏗️ Proje Mimarisi ve Paket Yapısı

```text
com.ipeksavas.pinknote
│
├── data
│   ├── dao
│   │   └── NoteDao.kt
│   ├── entity
│   │   └── NoteEntity.kt
│   ├── local
│   │   └── PinkNoteDatabase.kt
│   ├── navigation
│   │   └── PinkNoteNavGraph.kt
│   └── repository
│       └── NoteRepository.kt
│
├── presentation.notes
│   ├── AddNotesScreen.kt
│   ├── NoteScreen.kt
│   ├── NotesViewModelFactory.kt
│   └── NoteViewModel.kt
│
├── ui.theme
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
└── MainActivity.kt
```

---

## 🛠️ Kullanılan Teknolojiler
* **UI**: Jetpack Compose, Material Design 3
* **Mimari**: MVVM (Model-View-ViewModel)
* **Veritabanı**: Room Database
* **Navigasyon**: Jetpack Compose Navigation
* **Asenkron Programlama**: Kotlin Coroutines & Flow
* **Bağımlılık Enjeksiyonu**: Pure Kotlin (ViewModelFactory yaklaşımı)

---

## 📱 Pink Note Uygulamasının Ekran Görüntüleri

<p align="center">
  <img src="screenshots/1.JPEG" width="22%" alt="Image 1"/>
  &nbsp; &nbsp;
  <img src="screenshots/2.JPEG" width="22%" alt="Image 2"/>
  &nbsp; &nbsp;
  <img src="screenshots/3.JPEG" width="22%" alt="Image 3"/>
  &nbsp; &nbsp;
  <img src="screenshots/4.JPEG" width="22%" alt="Image 4"/>
</p>
