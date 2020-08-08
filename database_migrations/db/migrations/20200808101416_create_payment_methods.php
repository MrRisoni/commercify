<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class CreatePaymentMethods extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $paymentMethods = $this->table('payment_methods', ['signed' => false]);
        $paymentMethods->addColumn('title', 'string', ['limit' => 55])
            ->create();
    }
}
